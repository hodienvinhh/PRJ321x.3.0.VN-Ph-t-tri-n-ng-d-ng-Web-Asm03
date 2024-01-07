package com.example.assignment03.specification.specialization;

import com.example.assignment03.entity.Clinics;
import com.example.assignment03.entity.DoctorUser;
import com.example.assignment03.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClinicsSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<Clinics> buildWhere(String search) {

        Specification<Clinics> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomSpecification name = new CustomSpecification("name", search);
            CustomSpecification address = new CustomSpecification("address", search);
            CustomSpecification money = new CustomSpecification("costOfExamination", search);
            where = Specification.where(name).or(address).or(money);
        }
        return where;
    }
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Clinics> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Clinics> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("address")) {
            return criteriaBuilder.equal(root.get("address"),  "%" + value.toString() + "%");
        }
        if (field.equalsIgnoreCase("costOfExamination")) {
            return criteriaBuilder.like(root.get("costOfExamination"), "%" + value.toString() + "%");
        }

        if (field.equalsIgnoreCase("name")) {
            return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
        }
        if (field.equalsIgnoreCase("phone")) {
            return criteriaBuilder.like(root.get("phone"), "%" + value.toString() + "%");
        }
        return null;
    }


}
