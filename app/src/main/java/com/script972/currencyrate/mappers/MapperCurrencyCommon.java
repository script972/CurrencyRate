package com.script972.currencyrate.mappers;

import com.script972.currencyrate.domain.api.model.CurrencyResponce;
import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;
import com.script972.currencyrate.ui.model.CurrencySelectValueUi;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.utils.NumberUtils;

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
            model.setDate(DateUtils.soutDate(entity.getDate()));
            model.setRate(NumberUtils.convertToStringWithRound(entity.getRate(),1000000000.0));
            return model;
        }

        public static CurrencyValueEntity responceToEntity(CurrencyEntity curr, CurrencyResponce data) {
            CurrencyValueEntity currencyValue = new CurrencyValueEntity();
            currencyValue.setCurrencyId(curr.getId());
            currencyValue.setDate(DateUtils.entityDate(data.getDate()));
            currencyValue.setRate(data.getRate());
            return currencyValue;
        }

        public static CurrencySelectValueUi mapDbToMainUi(CurrencySelectValue entity) {
            CurrencySelectValueUi ui = new CurrencySelectValueUi();
            ui.setRate(NumberUtils.convertToStringWithRound(entity.getRate(), 100000.0));
            ui.setTitle(entity.getTitle());
            ui.setTitleShort(entity.getTitleShort());
            ui.setTopCurrency(entity.getTopCurrency());
            return ui;
        }
    }
}
