package org.tain.repository.mid;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tain.domain.mid.Comp;
import org.tain.info.mid.DivisionCompInfo;

@RepositoryRestResource
public interface CompRepository extends JpaRepository<Comp, Long>{

	@Query("select "
			+ "    d.code       as divCode "
			+ "    , d.title    as divTitle "
			+ "    , d.content  as divContent"
			+ "    , c.id       as compId    "
			+ "    , c.idx      as compIdx "
			+ "    , c.stmtKr   as compStmtKr "
			+ "    , c.stmtEn   as compStmtEn "
			+ "from "
			+ "  Comp c left outer join c.division d "
			+ "where 1=1 "
			//+ "order by c.id desc "
			+ "order by c.id asc "
			)
	List<DivisionCompInfo> getAll();
	
	@Query("select "
			+ "    d.code       as divCode "
			+ "    , d.title    as divTitle "
			+ "    , d.content  as divContent"
			+ "    , c.id       as compId    "
			+ "    , c.idx      as compIdx "
			+ "    , c.stmtKr   as compStmtKr "
			+ "    , c.stmtEn   as compStmtEn "
			+ "from "
			+ "  Comp c left outer join c.division d "
			+ "where 1=1 "
			//+ "order by c.id desc "
			+ "order by c.id asc "
			)
	Page<DivisionCompInfo> getPage(Pageable pageable);

	@Query("select "
			+ "    d.code       as divCode "
			+ "    , d.title    as divTitle "
			+ "    , d.content  as divContent"
			+ "    , c.id       as compId    "
			+ "    , c.idx      as compIdx "
			+ "    , c.stmtKr   as compStmtKr "
			+ "    , c.stmtEn   as compStmtEn "
			+ "from "
			+ "  Comp c left outer join c.division d "
			+ "where 1=1 "
			//+ "  and c.id = ?1 "
			+ "  and c.id = :id "
			)
	//DivisionCompInfo getById(Long id);
	DivisionCompInfo getById(@Param("id") Long id);
	
	// Sample
	@Query(value = ""
			+ " SELECT "
			+ "   g.*, "
			+ "   gm.* "
			+ " FROM "
			+ "   group g "
			+ "   LEFT JOIN group_members gm "
			+ "     ON g.group_id = gm.group_id "
			+ "       and gm.user_id = :userId "
			+ " WHERE 1=1 "
			+ "   and g.group_id = :groupId "
			, nativeQuery = true
			)
	DivisionCompInfo getGroupDetails(@Param("userId") Integer userId, @Param("groupId") Integer groupId);
}
