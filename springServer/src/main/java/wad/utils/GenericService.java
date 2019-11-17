package wad.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

@Service
public class GenericService {
    public <T> Spliterator<T> spliteratorOf(CrudRepository<T, Integer> repo) {
        return repo.findAll().spliterator();
    }

    public <T> List<T> fetchEntities(CrudRepository<T, Integer> repo) {
        return stream(spliteratorOf(repo), false).collect(toList());
    }
}
