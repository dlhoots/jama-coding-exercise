package hoots.dl.jamaex.market.item;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * JDBC repository which holds {@link Item}s.
 * 
 * @author David Hoots
 * @since 0.1
 */
@Repository
public class JdbcItemRepository implements ItemRepository {
  private JdbcTemplate jdbcTemplate;
  private final BeanPropertyRowMapper<Item.Builder> builderMapper = new BeanPropertyRowMapper<>(Item.Builder.class);

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * @throws ItemNotFoundException
   *           when there is no matching Item for the ID given
   */
  @Override
  public Item findItem(String id) {
    String sql = "SELECT id, cost, costCalculatorId FROM Item WHERE id = ?";
    try {
      return ((Item.Builder) jdbcTemplate.queryForObject(sql, new Object[] { id }, builderMapper)).build();
    } catch (EmptyResultDataAccessException ex) {
      throw new ItemNotFoundException(id);
    }
  }
}
