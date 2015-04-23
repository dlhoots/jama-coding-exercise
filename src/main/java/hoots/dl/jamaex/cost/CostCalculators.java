package hoots.dl.jamaex.cost;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * Holds a collection of all available {@link CostCalculators}
 * 
 * @author David Hoots
 * @since 0.1
 */
@Service
public class CostCalculators {

  // Unique IDs for all available CostCalculators
  // This are short only because they're stored as values in the database.
  public enum ID {
    FLAT, N4M5F3
  }

  private final Map<ID, CostCalculator> allCalculators = new HashMap<>();

  public CostCalculators() {
    allCalculators.put(ID.FLAT, new FlatRateCostCalculator());
    allCalculators.put(ID.N4M5F3, new NForMCostCalculator(5, 3));
  }

  public CostCalculator getCostCalculator(final ID id) {
    return allCalculators.get(id);
  }
}
