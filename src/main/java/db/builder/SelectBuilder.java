package db.builder;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilder {

    private static final String FROM_PRODUCT = "from `product` ";
    private static final String WHERE = "where ";
    private static final String AND = " and ";
    private static final String OFFSET = "offset ";
    private static final String LIMIT = "limit ";
    private static final String SELECT_ALL = "select * ";
    private static final String SELECT = "select ";

    private List<String> selectList = new ArrayList<>();

    private List<String> innerJoin = new ArrayList<>();

    private List<String> whereList = new ArrayList<>();

    private String orderBy;

    private List<Object> parameters = new ArrayList<>();

    private int objectLimit;

    private int objectOffset;

    public SelectBuilder innerJoin(String innerJoin){
        this.innerJoin.add(innerJoin);
        return this;
    }

    public SelectBuilder orderBy(String string){
        this.orderBy = string;
        parameters.add(string);
        return this;
    }

    public SelectBuilder where(String string){
        whereList.add(string);
        parameters.add(string);
        return this;
    }

    public SelectBuilder offset(int offset){
        this.objectOffset = offset;
        parameters.add(offset);
        return this;
    }

    public SelectBuilder limit(int limit){
        this.objectLimit = limit;
        parameters.add(limit);
        return this;
    }

    public SelectBuilder select(String string){
        selectList.add(string);
        return this;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public String productsBuild() {
        return getAssembleSelect() +
                getFromTable() +
                getJoinTables() +
                getWhere() +
                getOrderBy() +
                getLimit() +
                getOffset();
    }

    public String productCountBuild(){
        return getAssembleSelect() +
                getFromTable() +
                getWhere();
    }

    private String getOrderBy() {
        return orderBy != null ? orderBy : "";
    }

    private String getWhere() {
        if (whereList.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder(WHERE);
        for (int i = 0; i < whereList.size(); i++) {
            builder.append(whereList.get(i));
            if (i != whereList.size() - 1){
                builder.append(AND);
            }
        }
        return builder.toString();
    }

    private String getOffset() {
        return OFFSET + objectOffset + " ";
    }

    private String getLimit() {
        return LIMIT + objectLimit + " ";
    }

    private String getFromTable() {
        return FROM_PRODUCT;
    }

    private String getJoinTables() {
        StringBuilder builder = new StringBuilder();
        for (String inner : innerJoin){
            builder.append(inner);
        }
        return builder.toString();
    }

    private String getAssembleSelect() {
        if (selectList.isEmpty()){
            return SELECT_ALL;
        }
        return getSelectWithComma();
    }

    private String getSelectWithComma(){
        StringBuilder builder = new StringBuilder(SELECT);
        for (int i = 0; i < selectList.size(); i++) {
            builder.append(selectList.get(i)).append(" ");
            if (i != selectList.size() - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
