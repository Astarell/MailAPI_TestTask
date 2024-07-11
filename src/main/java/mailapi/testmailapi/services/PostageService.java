package mailapi.testmailapi.services;

import jakarta.transaction.Transactional;
import mailapi.testmailapi.models.PackageTracking;
import mailapi.testmailapi.models.PostOffice;
import mailapi.testmailapi.models.Postage;
import mailapi.testmailapi.projections.PostageView;
import mailapi.testmailapi.repositories.PackageTrackingRepository;
import mailapi.testmailapi.repositories.PostOfficeRepository;
import mailapi.testmailapi.repositories.PostageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Transactional
public class PostageService implements BasicServiceOptions<PostageView, Postage>{

    private PostageRepository postageRepo;
    private PostOfficeRepository postOfficeRepo;
    private PackageTrackingRepository packageTrackingRepo;

    @Autowired
    public PostageService(PostageRepository postageRepo,
                          PostOfficeRepository postOfficeRepo,
                          PackageTrackingRepository packageTrackingRepo){
        this.postageRepo = postageRepo;
        this.postOfficeRepo = postOfficeRepo;
        this.packageTrackingRepo = packageTrackingRepo;
    }

    @Override
    public Iterable<PostageView> findAll() {
        return postageRepo.customFindAll();
    }

    @Override
    public Optional<PostageView> findById(Long id) {
        return postageRepo.findById(id, PostageView.class);
    }

    @Override
    public void add(Postage entity) {
        PostOffice currentPostOffice = postOfficeRepo.findById(entity.getOffice().getId()).orElse(null);
        if(currentPostOffice == null)
            return;

        entity.setOffice(currentPostOffice);

        postageRepo.save(entity);
    }

    @Override
    public void update(Postage patchEntity) {
        Postage currentPostage = postageRepo.findById(patchEntity.getId(), Postage.class).orElse(null);
        if(currentPostage == null){
            return;
        }

        if(!currentPostage.getOffice().getId().equals(patchEntity.getOffice().getId())){
            PostOffice currentOffice = postOfficeRepo.findById(patchEntity.getOffice().getId(), PostOffice.class).orElse(null);

            if(currentOffice == null){
                return;
            }

            currentPostage.setOffice(currentOffice);
        }

        postageRepo.save(currentPostage);
    }

    @Override
    public void deleteById(Long id) {
        if(postageRepo.existsById(id)){
            PackageTracking currentPackageTracking = packageTrackingRepo.findById(id).orElse(null);
            if(currentPackageTracking != null){
                packageTrackingRepo.delete(currentPackageTracking);
            }

            postageRepo.deleteById(id);
        }
    }

    @Override
    public void delete(Postage entity) {
        PackageTracking currentPackageTracking = packageTrackingRepo.findById(entity.getId()).orElse(null);
        Postage postage = postageRepo.findById(entity.getId()).orElse(null);
        if(postage == null || currentPackageTracking == null)
            return;

        packageTrackingRepo.delete(currentPackageTracking);
        postageRepo.delete(postage);
    }
}
