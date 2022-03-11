package rmi;

public class TextMessage implements Message<String> {

	private String body;
	@Override
	public void setBody(String body) {
		this.body = body;

	}

	@Override
	public String getBody() {
		return body;
	}

}
