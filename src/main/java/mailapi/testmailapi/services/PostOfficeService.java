package mailapi.testmailapi.services;

import jakarta.transaction.Transactional;
import mailapi.testmailapi.models.PostOffice;
import mailapi.testmailapi.projections.PostOfficeView;
import mailapi.testmailapi.repositories.PostOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PostOfficeService implements BasicServiceOptions<PostOfficeView, PostOffice>{

    private PostOfficeRepository postOfficeRepo;

    @Autowired
    public PostOfficeService(PostOfficeRepository postOfficeRepo){
        this.postOfficeRepo = postOfficeRepo;
    }

    @Override
    public Iterable<PostOfficeView> findAll() {
        return postOfficeRepo.customFindAll();
    }

    @Override
    public Optional<PostOfficeView> findById(Long id) {
        return postOfficeRepo.findById(id, PostOfficeView.class);
    }

    @Override
    public void add(PostOffice entity) {
        postOfficeRepo.save(entity);
    }

    @Override
    public void update(PostOffice patchEntity) {
        PostOffice currentPostOffice = postOfficeRepo.findById(patchEntity.getId(), PostOffice.class).orElse(null);

        if(currentPostOffice == null){
            return;
        }

        if(!currentPostOffice.getName().equals(patchEntity.getName())){
            currentPostOffice.setName(patchEntity.getName());
        }
        if(!currentPostOffice.getOfficeAddress().equals(patchEntity.getOfficeAddress())){
            currentPostOffice.setOfficeAddress(patchEntity.getOfficeAddress());
        }

        postOfficeRepo.save(currentPostOffice);
    }

    @Override
    public void deleteById(Long id) {
        PostOffice currentPostOffice = postOfficeRepo.findById(id).orElse(null);
        if(currentPostOffice == null)
            return;

        currentPostOffice.getPostages().forEach(postage -> postage.setOffice(null));
        postOfficeRepo.deleteById(id);
    }

    @Override
    public void delete(PostOffice entity) {
        PostOffice currentPostOffice = postOfficeRepo.findById(entity.getId()).orElse(null);
        if(currentPostOffice == null)
            return;

        currentPostOffice.getPostages().forEach(postage -> postage.setOffice(null));
        postOfficeRepo.delete(currentPostOffice);
    }
}
