package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="doc_tab")
public class Doctor {
	@Id
	@Column(name="doc_id_col")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long docId;
	
	@Column(name="doc_name_col")
	private String docName;
	
	@Column(name="doc_email_col")
	private String docEmail;
	
	@Column(name="doc_addr_col")
	private String docAddr;
	
	@Column(name="doc_mob_col")
	private String docMobile;
	
	@Column(name="doc_gen_col")
	private String docGen;
	
	@Column(name="doc_note_col")
	private String docNote;
	
	@Column(name="doc_img_url_col")
	private String imageUrl;
	
	//====Module Integration
	@ManyToOne
	@JoinColumn(name="spec_id_fk_col")
	private Specialization specialization;
}
