package mailapi.testmailapi.services;

import jakarta.transaction.Transactional;
import mailapi.testmailapi.controllers.PackageTrackingController;
import mailapi.testmailapi.custom_enums.TrackingStatus;
import mailapi.testmailapi.forms.TrackingUpdateForm;
import mailapi.testmailapi.models.PackageTracking;
import mailapi.testmailapi.models.PostOffice;
import mailapi.testmailapi.models.Postage;
import mailapi.testmailapi.pojos.ShippingInformation;
import mailapi.testmailapi.projections.PackageTrackingView;
import mailapi.testmailapi.repositories.PackageTrackingRepository;
import mailapi.testmailapi.repositories.PostOfficeRepository;
import mailapi.testmailapi.repositories.PostageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class PackageTrackingService implements BasicServiceOptions<PackageTrackingView, PackageTracking>{

    private PackageTrackingRepository packageTrackingRepo;
    private PostageRepository postageRepo;
    private PostOfficeRepository postOfficeRepo;

    @Autowired
    public PackageTrackingService(PackageTrackingRepository packageTrackingRepo,
                                  PostageRepository postageRepo,
                                  PostOfficeRepository postOfficeRepo){
        this.packageTrackingRepo = packageTrackingRepo;
        this.postageRepo = postageRepo;
        this.postOfficeRepo = postOfficeRepo;
    }

    @Override
    public Iterable<PackageTrackingView> findAll() {
        return packageTrackingRepo.customFindAll();
    }

    @Override
    public Optional<PackageTrackingView> findById(Long id) {
        return packageTrackingRepo.findById(id, PackageTrackingView.class);
    }

    @Override
    public void add(PackageTracking entity) {
        Postage currentPostage = postageRepo.findById(entity.getPostage().getId()).orElse(null);
        if(currentPostage == null){
            return;
        }

        entity.setPostage(currentPostage);

        ShippingInformation initialTrackingInfo = new ShippingInformation.ShipInfoBuilder()
                .setPostOfficeName(entity.getPostage().getOffice().getName())
                .setPostOfficeAddress(entity.getPostage().getOffice().getOfficeAddress())
                .setArrivedAt(String.valueOf(entity.getCreatedAt()))
                .build();

        entity.getShippingInformation().add(initialTrackingInfo);
        entity.setStatus(TrackingStatus.IN_TRANSIT);

        packageTrackingRepo.save(entity);
    }

    public void reassign(TrackingUpdateForm trackForm){
        PackageTracking currentTrack = packageTrackingRepo.findById(trackForm.getTrackId(), PackageTracking.class).orElse(null);
        PostOffice currentOffice = postOfficeRepo.findById(trackForm.getOfficeId(), PostOffice.class).orElse(null);

        if(currentTrack == null || currentOffice == null){
            return;
        }

        if(trackForm.isReceived()){
            currentTrack.setStatus(TrackingStatus.RECEIVED);
            currentTrack.getShippingInformation().getLast().setDeparturedAt(String.valueOf(LocalDateTime.now()));

            packageTrackingRepo.save(currentTrack);
            return;
        }

        Postage currentPostage = postageRepo.findById(currentTrack.getId()).orElse(null);
        if(currentPostage == null){
            return;
        }

        currentPostage.setOffice(currentOffice);

        ShippingInformation additionalInfo = new ShippingInformation.ShipInfoBuilder()
                .setPostOfficeName(currentOffice.getName())
                .setPostOfficeAddress(currentOffice.getOfficeAddress())
                .build();

        currentTrack.getShippingInformation().getLast().setDeparturedAt(String.valueOf(LocalDateTime.now()));
        currentTrack.getShippingInformation().add(additionalInfo);

        if(currentTrack.getOfficeEndpoint().equals(currentOffice.getName())){
            currentTrack.setStatus(TrackingStatus.ARRIVED);
        }

        postageRepo.save(currentPostage);
        packageTrackingRepo.save(currentTrack);
    }

    @Override
    public void update(PackageTracking patchEntity) {
        return;
    }

    @Override
    public void deleteById(Long id) {
        packageTrackingRepo.deleteById(id);
    }

    @Override
    public void delete(PackageTracking entity) {
        PackageTracking currentPackageTracking = packageTrackingRepo.findById(entity.getId()).orElse(null);
        if(currentPackageTracking == null)
            return;

        packageTrackingRepo.delete(currentPackageTracking);
    }
}
