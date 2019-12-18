
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.CommercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	//Internal state -----------------------------------

	@Autowired
	private SponsorBannerRepository repository;


	//AbstractCreateService<Sponsoor, Banner> interface --------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		boolean result;

		Principal principal;
		int sponsorId;
		Sponsor sponsor;

		principal = request.getPrincipal();
		sponsorId = principal.getActiveRoleId();
		sponsor = this.repository.findOneSponsorById(sponsorId);

		result = sponsor.getCreditCard() != null;

		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creditCard", "sponsor");

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
		assert request != null;

		CommercialBanner result;
		Principal principal;
		int sponsorId;
		Sponsor sponsor;

		principal = request.getPrincipal();
		sponsorId = principal.getActiveRoleId();
		sponsor = this.repository.findOneSponsorById(sponsorId);

		result = new CommercialBanner();
		result.setSponsor(sponsor);
		CreditCard creditCard = sponsor.getCreditCard();
		result.setCreditCard(creditCard);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("slogan")) {
			double threshold = this.repository.findThreshold();
			String[] spanishWords = this.repository.findSpanishWords().split(", ");
			String[] englishWords = this.repository.findEnglishWords().split(", ");
			double numberWordsBody = entity.getSlogan().split("\\s|\\.|\\,").length;

			double spamWords = 0.;
			for (String s : spanishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
			}
			for (String s : englishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
			}

			errors.state(request, spamWords / numberWordsBody * 100 <= threshold, "slogan", "sponsor.banner.form.error.spam");

		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<CommercialBanner> request, final Response<CommercialBanner> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
