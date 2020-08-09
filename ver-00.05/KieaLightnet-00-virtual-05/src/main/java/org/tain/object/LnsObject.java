package org.tain.object;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_lnsobject"
	, indexes = {
			@Index(name = "lnsobject_tranid_idx", unique = true, columnList = "transaction_id"),
	}
)
@SequenceGenerator(name = "lnsobject_seq"
	, sequenceName = "lnsobject_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class LnsObject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lnsobject_seq")
	@Column(name = "id")
	private Long id;
	
	// info ----------------------------------------
	@Column(name = "title", length = 128)
	private String title;
	
	// transaction Id ----------------------------------------
	@Column(name = "transaction_id", length = 32)
	private String transactionId;
	
	// request ----------------------------------------
	@Column(name = "req_fep_info", length = 128)   // REQ 100 byte
	private String reqFepInfo;
	
	@Column(name = "req_data", length = 512)
	private String reqData;
	
	@Column(name = "req_code", length = 16)        // SUCCESS / FAIL
	private String reqCode;
	
	@Column(name = "req_message", length = 128)
	private String reqMessage;
	
	@Lob
	@Column(name = "req_stream")
	private String reqStream;
	
	@Lob
	@Column(name = "req_json")
	private String reqJson;
	
	// response ----------------------------------------
	@Column(name = "res_fep_info", length = 128)   // RES 100 byte
	private String resFepInfo;
	
	@Column(name = "res_data", length = 512)
	private String resData;
	
	@Column(name = "res_code", length = 16)        // SUCCESS / FAIL
	private String resCode;
	
	@Column(name = "res_message", length = 128)
	private String resMessage;
	
	@Lob
	@Column(name = "res_stream")
	private String resStream;
	
	@Lob
	@Column(name = "res_json")
	private String resJson;
	
	// time  ----------------------------------------
	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP) // @Temporal should only be set on a java.util.Date or java.util.Calendar property
	//@JsonProperty(access = Access.WRITE_ONLY)
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "cre_date")
	private LocalDateTime creDate;
	//private Timestamp creDate;
	//private Date creDate;
	
	@UpdateTimestamp
	//@Temporal(TemporalType.TIMESTAMP) // @Temporal should only be set on a java.util.Date or java.util.Calendar property
	//@JsonProperty(access = Access.WRITE_ONLY)
	//@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "upd_date")
	private LocalDateTime updDate;
	//private Timestamp updDate;
	//private Date updDate;
	
	@Builder
	public LnsObject(
			String title,
			String transactionId,
			String reqFepInfo,
			String reqStream,
			String reqJson,
			String resFepInfo,
			String resStream,
			String resJson
			) {
		this.title = title;
		this.transactionId = transactionId;
		this.reqFepInfo = reqFepInfo;
		this.reqStream = reqStream;
		this.reqJson = reqJson;
		this.resFepInfo = resFepInfo;
		this.resStream = resStream;
		this.resJson = resJson;
	}
}
