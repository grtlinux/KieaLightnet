package org.tain.domain.apis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Apis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", length = 128)
	private String name;
	
	@Column(name = "http_path", length = 64)
	private String httpPath;
	
	@Column(name = "http_method", length = 16)
	private String httpMethod;
	
	@Column(name = "req_json", length = 1024000)
	private String reqJson;
	
	@Builder
	public Apis(
			String name,
			String httpPath,
			String httpMethod,
			String reqJson
			) {
		this.name = name;
		this.httpPath = httpPath;
		this.httpMethod = httpMethod;
		this.reqJson = reqJson;
	}
}
