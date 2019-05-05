package db.specification;

import java.util.List;

public interface SQLSpecification {

    String toMySqlQuery();

    String countMySqlQuery();

    List<Object> getParams();

}
