package com.script972.currencyrate.mappers;

import android.util.Log;

import com.script972.currencyrate.api.model.CurrencyResponce;
import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.utils.NumberUtils;

import java.util.Calendar;

public class MapperCurrencyCommon {
    public static class MapperCurrency {

        public static CurrencyEntity mapNetworkToDb(CurrencyResponce model) {
            CurrencyEntity entity = new CurrencyEntity();
            entity.setIndex(model.getIndex());
            entity.setTitle(model.getTitle());
            entity.setTitleShort(model.getTitleShort());
            return entity;
        }

    }

    public static class MapperCurrencyValue {

        public static CurrencyValueEntity mapNetworkToDb(CurrencyResponce currencyResponce) {
            CurrencyValueEntity out = new CurrencyValueEntity();
            out.setRate(currencyResponce.getRate());
            out.setDate(DateUtils.entityDate(currencyResponce.getDate()));
            return out;
        }

        public static CurrencyValueModel mapDbToUi(CurrencyValueEntity entity) {
            CurrencyValueModel model = new CurrencyValueModel();
            model.setId(entity.getId());
            model.setDate(DateUtils.soutDate(entity.getDate()));
            model.setRate(NumberUtils.convertToStringWithRound(entity.getRate()));
            return model;
        }

        public static CurrencyValueEntity responceToEntity(CurrencyEntity curr, CurrencyResponce data) {
            CurrencyValueEntity currencyValue = new CurrencyValueEntity();
            currencyValue.setCurrencyId(curr.getId());
            currencyValue.setDate(DateUtils.entityDate(data.getDate()));
            currencyValue.setRate(data.getRate());
            return currencyValue;
        }
    }
}
