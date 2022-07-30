package bg.softuni.FindYourHome.repository;

import bg.softuni.FindYourHome.model.dtos.SearchOfferDTO;
import bg.softuni.FindYourHome.model.entity.OfferEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class OfferSpecification implements Specification<OfferEntity> {
    private final SearchOfferDTO searchOfferDTO;

    public OfferSpecification(SearchOfferDTO searchOfferDTO) {
        this.searchOfferDTO = searchOfferDTO;
    }

    @Override
    public Predicate toPredicate(Root<OfferEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchOfferDTO.getCity() != null && !searchOfferDTO.getCity().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.join("city").get("name"), searchOfferDTO.getCity()))
            );
        }

        if (searchOfferDTO.getMinPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchOfferDTO.getMinPrice()))
            );
        }

        if (searchOfferDTO.getMaxPrice() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("price"), searchOfferDTO.getMaxPrice()))
            );
        }

        return p;
    }
}
