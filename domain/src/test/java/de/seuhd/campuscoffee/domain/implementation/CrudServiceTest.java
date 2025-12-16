package de.seuhd.campuscoffee.domain.implementation;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.ports.data.CrudDataService;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;

class MockDataService implements CrudDataService<Review, Long> {
    @Override
    public void clear() {}

    @Override
    public @NonNull List<Review> getAll() {
        return List.of();
    }

    @Override
    public @NonNull Review getById(@NonNull Long id) {
        return TestFixtures.getReviewFixtures().getFirst();
    }

    @Override
    public @NonNull Review upsert(@NonNull Review entity) {
        return TestFixtures.getReviewFixtures().getFirst();
    }

    @Override
    public void delete(@NonNull Long id) {}
    
}

class CrudService extends CrudServiceImpl<Review, Long> {
    private CrudDataService<Review, Long> dataService;

    public CrudService(CrudDataService<Review, Long> dataService) {
        super(Review.class);
        this.dataService = dataService;
    }

    @Override
    protected CrudDataService<Review, Long> dataService() {
        return dataService;
    }

}

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {
    private CrudService crudService;
    private MockDataService dataService = mock(MockDataService.class);

    @BeforeEach
    void beforeEach() {
        crudService = new CrudService(dataService);
    }

    @Test
    void testClear() {
        // when
        crudService.clear();

        // then
        verify(dataService).clear();
    }

    @Test
    void testDelete() {
        // when
        crudService.delete(0L);

        // then
        verify(dataService).delete(0L);
    }
}
