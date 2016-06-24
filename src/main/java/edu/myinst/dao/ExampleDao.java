package ${basePackage}.dao;

import blackboard.persist.Id;
import blackboard.persist.dao.impl.SimpleDAO;
import blackboard.persist.impl.SimpleSelectQuery;
import blackboard.persist.impl.mapping.DbObjectMap;
import blackboard.persist.impl.mapping.annotation.AnnotationMappingFactory;
import blackboard.platform.query.Criteria;
import ${basePackage}.model.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExampleDao extends SimpleDAO<Example> {
    final Logger logger = LoggerFactory.getLogger(Example.class);
    private static final DbObjectMap EXAMPLE_EXT_MAP = AnnotationMappingFactory.getMap(Example.class);

    public ExampleDao() {
        super(EXAMPLE_EXT_MAP);
    }

    public List<Example> loadByUserId(Id userId) {
        SimpleSelectQuery query = new SimpleSelectQuery(this.getDAOSupport().getMap());

        Criteria criteria = query.getCriteria();

        criteria.add(criteria.equal("userId", userId));

        return getDAOSupport().loadList(query);
    }
}