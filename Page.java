import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{

	public int page;
	public int per_page;
	public int total;
	public int total_pages;
	public List<Data> data;
	
}
