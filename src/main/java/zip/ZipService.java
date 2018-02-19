package zip;

import infrastructure.Query;

public class ZipService {

    private LocationRepository repository;

    public ZipService(LocationRepository repository) {
        this.repository = repository;
    }

    public ZipInfo dataFor(String country, String zipCode) {
        return repository.findBy(new Query(country, zipCode));
    }

}