package data;

public class Picture {
	private int id;
	private int idLevel;
	private int ref_word;
	private int ref_level;
	private String link_to_file;
	
	public Picture(){
		
	}
	public Picture(int id, int idLevel, int ref_word, int ref_level,
			String link_to_file) {
		super();
		this.id = id;
		this.idLevel = idLevel;
		this.ref_word = ref_word;
		this.ref_level = ref_level;
		this.link_to_file = link_to_file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public int getRef_word() {
		return ref_word;
	}

	public void setRef_word(int ref_word) {
		this.ref_word = ref_word;
	}

	public int getRef_level() {
		return ref_level;
	}

	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}

	public String getLink_to_file() {
		return link_to_file;
	}

	public void setLink_to_file(String link_to_file) {
		this.link_to_file = link_to_file;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idLevel;
		result = prime * result
				+ ((link_to_file == null) ? 0 : link_to_file.hashCode());
		result = prime * result + ref_level;
		result = prime * result + ref_word;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Picture other = (Picture) obj;
		if (id != other.id)
			return false;
		if (idLevel != other.idLevel)
			return false;
		if (link_to_file == null) {
			if (other.link_to_file != null)
				return false;
		} else if (!link_to_file.equals(other.link_to_file))
			return false;
		if (ref_level != other.ref_level)
			return false;
		if (ref_word != other.ref_word)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", idLevel=" + idLevel + ", ref_word="
				+ ref_word + ", ref_level=" + ref_level + ", link_to_file="
				+ link_to_file + "]";
	}
	
	
	
}
