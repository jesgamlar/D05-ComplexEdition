
package acme.features.administrator.commercialBanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.CommercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCommercialBannerCreateService implements AbstractCreateService<Administrator, CommercialBanner> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorCommercialBannerRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "url", "slogan");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;

		result = new CommercialBanner();

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("creditcarddeadline")) {
			errors.state(request, request.getModel().getString("creditcarddeadline") != null, "creditcarddeadline", "administrator.commercial-banner.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("creditcarddeadline")) {
			errors.state(request, request.getModel().getString("creditcarddeadline").matches("^(0[1-9]|1[0-2])\\/[0-9][0-9]$"), "creditcarddeadline", "administrator.commercial-banner.form.error.deadlinePattern");
		}

		if (!errors.hasErrors("creditcarddeadline")) {
			Date currentDate = new Date(System.currentTimeMillis());

			String[] monthYear = request.getModel().getString("creditcarddeadline").split("/");
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

			errors.state(request, deadline.after(currentDate), "creditcarddeadline", "administrator.commercial-banner.form.error.deadline");
		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;
		;

		this.repository.save(entity);
	}

}
