/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.sponsor.creditCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCreditCardCreateService implements AbstractCreateService<Sponsor, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorCreditCardRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return this.repository.findOneSponsorById(request.getPrincipal().getActiveRoleId()).getCreditCard() == null;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			errors.state(request, request.getModel().getString("deadline") != null, "deadline", "sponsor.creditCard.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, request.getModel().getString("deadline").matches("^(0[1-9]|1[0-2])\\/[0-9][0-9]$"), "deadline", "sponsor.creditCard.form.error.deadlinePattern");
		}

		if (!errors.hasErrors("deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());

			String[] monthYear = request.getModel().getString("deadline").split("/");
			String deadlineString = monthYear[0] + "/20" + monthYear[1];
			Date deadline = new Date();

			try {
				deadline = new SimpleDateFormat("MM/yyyy").parse(deadlineString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(deadline);

			calendar.add(Calendar.HOUR, 1);

			deadline = calendar.getTime();

			errors.state(request, deadline.after(currentDate), "deadline", "sponsor.creditCard.form.error.deadline");

		}
		if (!errors.hasErrors("number")) {
			errors.state(request, request.getModel().getString("number").matches("5[1-5][0-9]{14}$"), "number", "sponsor.creditCard.form.error.numberPattern");
		}
		if (!errors.hasErrors("cvv")) {
			errors.state(request, request.getModel().getString("cvv").matches("^\\d{3,4}$"), "cvv", "sponsor.creditCard.form.error.cvvPattern");
		}
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holder", "brand", "deadline", "number", "cvv");
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result = new CreditCard();
		return result;
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		Sponsor s = this.repository.findOneSponsorById(request.getPrincipal().getActiveRoleId());
		s.setCreditCard(entity);
		this.repository.save(entity);
		this.repository.save(s);
	}

}
