package models;

import java.util.List;

import entitys.Replay_E;
import interfaces.Replay_I;

public class Replay_M implements Replay_I{

	@Override
	public List<Replay_E> getAllReplies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Replay_E getReplyById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createReply(Replay_E reply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReply(Replay_E reply) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteReply(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Replay_E> getRepliesByTopicId(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Replay_E> getRepliesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
