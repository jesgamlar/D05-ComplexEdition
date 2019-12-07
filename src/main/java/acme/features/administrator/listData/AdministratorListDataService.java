
package acme.features.administrator.listData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.requestEntity.RequestEntity;
import acme.form.ListData;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorListDataService implements AbstractShowService<Administrator, ListData> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorListDataRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<ListData> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ListData> request, final ListData entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberAnnouncements", "numberCompanies", "numberInvestors", "minRewardRequest", "maxRewardRequest", "averageRewardRequest", "derivationRewardRequest", "minRewardOffer", "maxRewardOffer", "averageRewardOffer",
			"derivationRewardOffer");

	}

	@Override
	public ListData findOne(final Request<ListData> request) {
		assert request != null;

		ListData result = new ListData();

		Money m1 = new Money();
		m1.setCurrency("EUR");
		Money m2 = new Money();
		m2.setCurrency("EUR");
		Money m3 = new Money();
		m3.setCurrency("EUR");
		Money m4 = new Money();
		m4.setCurrency("EUR");
		Money m5 = new Money();
		m5.setCurrency("EUR");
		Money m6 = new Money();
		m6.setCurrency("EUR");
		Money m7 = new Money();
		m7.setCurrency("EUR");
		Money m8 = new Money();
		m8.setCurrency("EUR");

		result.setNumberAnnouncements(this.repository.numbAnn());
		result.setNumberCompanies(this.repository.numbComp());
		result.setNumberInvestors(this.repository.numbInv());

		List<Double> money1 = new ArrayList<>();

		Double dv1 = 0.;

		for (Offer oferta : this.repository.offer()) {
			money1.add(oferta.getMaxMoney().getAmount());
			money1.add(oferta.getMinMoney().getAmount());
		}

		Double avg1 = money1.stream().mapToDouble(Double::doubleValue).average().getAsDouble();

		for (Offer oferta : this.repository.offer()) {
			dv1 = dv1 + Math.pow(oferta.getMaxMoney().getAmount() + oferta.getMinMoney().getAmount() / 2 - avg1, 2) / (this.repository.offer().size() * 2 - 1);
		}

		dv1 = Math.sqrt(dv1);

		m1.setAmount(avg1);
		result.setAverageRewardOffer(m1);
		m2.setAmount(Collections.min(money1));
		result.setMinRewardOffer(m2);
		m3.setAmount(Collections.max(money1));
		result.setMaxRewardOffer(m3);
		m4.setAmount(dv1);
		result.setDerivationRewardOffer(m4);

		List<Double> money2 = new ArrayList<>();

		Double dv2 = 0.;

		for (RequestEntity oferta : this.repository.request()) {
			money2.add(oferta.getRewardMax().getAmount());
			money2.add(oferta.getRewardMin().getAmount());
		}

		Double avg2 = money2.stream().mapToDouble(Double::doubleValue).average().getAsDouble();

		for (RequestEntity oferta : this.repository.request()) {
			dv2 = dv2 + Math.pow(oferta.getRewardMax().getAmount() + oferta.getRewardMin().getAmount() / 2 - avg2, 2) / (this.repository.request().size() * 2 - 1);
		}

		dv2 = Math.sqrt(dv2);

		m5.setAmount(avg2 / this.repository.request().size());
		result.setAverageRewardRequest(m5);
		m6.setAmount(Collections.min(money2));
		result.setMinRewardRequest(m6);
		m7.setAmount(Collections.max(money2));
		result.setMaxRewardRequest(m7);
		m8.setAmount(dv2);
		result.setDerivationRewardRequest(m8);

		return result;
	}

}
