package vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaskEsInfo implements Serializable {
	private Integer cid;

	private String taskId;

	private String taskTitle;

	private String content;

	private BigDecimal money;

	private String masterId;

	private Byte state;

	private String createDate;

	private String updatedate;

	private String deadLineDate;

	private String sourcetype;

	private String name;

	private String sex;

	private Short masterlevel;

	private String txUrl;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(String deadLineDate) {
		this.deadLineDate = deadLineDate;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Short getMasterlevel() {
		return masterlevel;
	}

	public void setMasterlevel(Short masterlevel) {
		this.masterlevel = masterlevel;
	}

	public String getTxUrl() {
		return txUrl;
	}

	public void setTxUrl(String txUrl) {
		this.txUrl = txUrl;
	}
}
