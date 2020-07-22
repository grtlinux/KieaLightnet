package org.tain.object;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_lnspacket"
	, indexes = {
			//@Index(name = "lnspacket_tranid_idx", unique = false, columnList = "transaction_id"),
	}
)
@SequenceGenerator(name = "lnspacket_seq"
	, sequenceName = "lnspacket_seq"
	, initialValue = 1
	, allocationSize = 1
)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {})
public class LnsPacket {

}
