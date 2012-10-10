package data;


// Generated 31 ���� 2011 8:58:00 by Hibernate Tools 3.2.1.GA


public class SingleGame implements java.io.Serializable {
	private int id;
	private int idLevelCur;
	private int pictureCount;
	private boolean isLetterRemoved;
	
	public SingleGame() {
	}
	
	public SingleGame(int id, int idLevelCur, int pictureCount,
			boolean isLetterRemoved) {
		this.id = id;
		this.idLevelCur = idLevelCur;
		this.pictureCount = pictureCount;
		this.isLetterRemoved = isLetterRemoved;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdLevelCur() {
		return idLevelCur;
	}
	public void setIdLevelCur(int idLevelCur) {
		this.idLevelCur = idLevelCur;
	}
	public int getPictureCount() {
		return pictureCount;
	}
	public void setPictureCount(int pictureCount) {
		this.pictureCount = pictureCount;
	}
	public boolean isLetterRemoved() {
		return isLetterRemoved;
	}
	public void setLetterRemoved(boolean isLetterRemoved) {
		this.isLetterRemoved = isLetterRemoved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idLevelCur;
		result = prime * result + (isLetterRemoved ? 1231 : 1237);
		result = prime * result + pictureCount;
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
		SingleGame other = (SingleGame) obj;
		if (id != other.id)
			return false;
		if (idLevelCur != other.idLevelCur)
			return false;
		if (isLetterRemoved != other.isLetterRemoved)
			return false;
		if (pictureCount != other.pictureCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SingleGame [id=" + id + ", idLevelCur=" + idLevelCur
				+ ", pictureCount=" + pictureCount + ", isLetterRemoved="
				+ isLetterRemoved + "]";
	}

}
