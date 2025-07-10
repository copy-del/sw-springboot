package com.smhrd.web.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "datamember") // 해당 클래스가 datamember 테이블임을 나타내는 클래스임을 표시
public class DataMember {

	@Id // primary key
	@Column(name="id", length=100) // 해당하는 필드에 대응되는 테이블 컬럼의 디테일한 설정 가능
	private String id;
	@Column(nullable = false, length = 100)
	private String pw;
	@ColumnDefault("'users'")
	@Column(nullable=false,insertable=false)
	private String roles;
}
