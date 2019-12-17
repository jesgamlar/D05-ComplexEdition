
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select ut.user.id from UserThread ut where ut.messageThread.id = ?1")
	List<Integer> findManyUserIdByMessageThreadId(int id);

	@Query("select at.id from Authenticated at where at.userAccount.id = ?1")
	Integer findAuthenticatedIdByUserId(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyAll(int authId);

	@Query("select s.threshold from Spam s")
	double findThreshold();

	@Query("select s.spanishWords from Spam s")
	String findSpanishWords();

	@Query("select s.englishWords from Spam s")
	String findEnglishWords();

}
