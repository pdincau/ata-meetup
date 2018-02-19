package zip;

import infrastructure.Query;

public interface LocationRepository {
    ZipInfo findBy(Query query);
}
