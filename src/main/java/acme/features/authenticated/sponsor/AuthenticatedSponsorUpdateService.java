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

package acme.features.authenticated.sponsor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedSponsorUpdateService implements AbstractUpdateService<Authenticated, Sponsor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Sponsor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("creditCard.deadline")) {
			errors.state(request, request.getModel().getString("creditCard.deadline") != null, "creditcard.deadline", "authenticated.sponsor.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("creditCard.deadline")) {
			errors.state(request, request.getModel().getString("creditCard.deadline").matches("^(0[1-9]|1[0-2])\\/[0-9][0-9]$"), "creditCard.deadline", "authenticated.sponsor.form.error.deadlinePattern");
		}

		if (!errors.hasErrors("creditCard.deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());

			String[] monthYear = request.getModel().getString("creditCard.deadline").split("/");
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

			errors.state(request, deadline.after(currentDate), "creditcard.deadline", "authenticated.sponsor.form.error.deadline");

		}
		if (!errors.hasErrors("creditCard.number")) {
			errors.state(request, request.getModel().getString("creditCard.number").matches("5[1-5][0-9]{14}$"), "creditCard.number", "authenticated.sponsor.form.error.numberPattern");
		}
		if (!errors.hasErrors("creditCard.cvv")) {
			errors.state(request, request.getModel().getString("creditCard.cvv").matches("^\\d{3,4}$"), "creditCard.cvv", "authenticated.sponsor.form.error.cvvPattern");
		}
	}

	@Override
	public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisationName", "creditCard.holder", "creditCard.brand", "creditCard.deadline", "creditCard.number", "creditCard.cvv");
	}

	@Override
	public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

	@Override
	public Sponsor findOne(final Request<Sponsor> request) {

		return this.repository.findOneSponsorByUserAccountId(request.getPrincipal().getAccountId());
	}

	@Override
	public void update(final Request<Sponsor> request, final Sponsor entity) {
		assert request != null;
		assert entity != null;

		CreditCard c = new CreditCard();
		c = entity.getCreditCard();
		this.repository.save(c);
		this.repository.save(entity);

	}

}
