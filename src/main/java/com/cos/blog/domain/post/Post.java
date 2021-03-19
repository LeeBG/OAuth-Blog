package com.cos.blog.domain.post;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne
	@JoinColumn(name="userId") //@ManyToOne은 fetchType이 기본 eager 로 설정됨
	private User user;
	
	//양방향 매핑
	//나는 연관 관계의 주인이 아니야(FK안만듬) mappedBy
	//EAGER = post가서post를 select할 때(상세보기를 할때) 무조건 들고올 것이기 때문에 
	//갈때마다 post를 다들고 옴 listPage에서
	// LAZY = 왜냐하면 내가 list를 볼때마다 쓸데 없이 댓글
	//cascade = 하나의 게시글을 삭제할 때 게시글의 reply를 날릴것인지 아닌지를 설계
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"post"})	//누군가가 post select했는데  reply를 들고오고  post를 들고오고 무한매핑
	@OrderBy("id desc")
	private List<Reply> replys;
	
	
	@CreationTimestamp
	private Timestamp createDate;

	
}
