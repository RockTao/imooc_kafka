package com.imooc.kafka.common;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MessageEntity {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String body;

	@Override
	public String toString() {
		return "MessageEntity{" +
				"title='" + title + '\'' +
				", body='" + body + '\'' +
				'}';
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) {
//			return true;
//		}
//		if (o == null || getClass() != o.getClass()) {
//			return false;
//		}
//		MessageEntity that = (MessageEntity) o;
//		return Objects.equals(title, that.title) &&
//				Objects.equals(body, that.body);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(title, body);
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getBody() {
//		return body;
//	}
//
//	public void setBody(String body) {
//		this.body = body;
//	}
}
