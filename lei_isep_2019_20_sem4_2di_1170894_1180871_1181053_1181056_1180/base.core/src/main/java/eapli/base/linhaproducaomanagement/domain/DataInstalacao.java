package eapli.base.linhaproducaomanagement.domain;

import javax.persistence.Embeddable;

import java.util.Calendar;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;

@Embeddable
public class DataInstalacao implements ValueObject, Comparable<DataInstalacao> {

    private int year;
    private int month;
    private int day;

    /**
     * Represents the months of the year.
     */
    private static enum Month {

        /**
         * The months of the year.
         */
        JANUARY(31) {
            @Override
            public String toString() {
                return "January";
            }
        },
        FEBRUARY(28) {
            @Override
            public String toString() {
                return "February";
            }
        },
        MARCH(31) {
            @Override
            public String toString() {
                return "March";
            }
        },
        APRIL(30) {
            @Override
            public String toString() {
                return "April";
            }
        },
        MAY(31) {
            @Override
            public String toString() {
                return "May";
            }
        },
        JUNE(30) {
            @Override
            public String toString() {
                return "June";
            }
        },
        JULY(31) {
            @Override
            public String toString() {
                return "July";
            }
        },
        AUGUST(31) {
            @Override
            public String toString() {
                return "August";
            }
        },
        SEPTEMBER(30) {
            @Override
            public String toString() {
                return "September";
            }
        },
        OCTOBER(31) {
            @Override
            public String toString() {
                return "October";
            }
        },
        NOVEMBER(30) {
            @Override
            public String toString() {
                return "November";
            }
        },
        DECEMBER(31) {
            @Override
            public String toString() {
                return "December";
            }
        };

        /**
         * The number of days on a month.
         */
        private int numberOfDays;

        /**
         * Constructs a month with the number of days given.
         *
         * @param numberOfDays - Number of days on the month.
         */
        private Month(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        /**
         * Returns the number of days of the month of the given year.Devolve o número de dias do mês do year recebido por parâmetro.
         *
         * @param year - The year of the month.
         * @return
         */
        public int numberOfDays(int year) {
            if (ordinal() == 1 && DataInstalacao.isLeapYear(year)) {
                return numberOfDays + 1;
            }
            return numberOfDays;
        }

        /**
         * Devolve o mês cuja ordem é recebida por parâmetro.
         *
         * @param ordemDoMes a ordem do mês.
         * @return o mês cuja ordem é recebida por parâmetro.
         */
        public static Month obterMes(int ordemDoMes) {
            return Month.values()[ordemDoMes - 1];
        }
    }

    /**
     * Constrói uma instância de Data recebendo o year, o mês e o day.
     *
     * @param ano o year da data.
     * @param mes o mês da data.
     * @param dia o day da data.
     */
    public DataInstalacao(int ano, int mes, int dia) {
        if (StringPredicates.isNullOrEmpty(String.valueOf(ano)) && StringPredicates.isNullOrEmpty(String.valueOf(mes)) && StringPredicates.isNullOrEmpty(String.valueOf(dia))) {
            throw new IllegalArgumentException(
                    "data de instalacao should neither be null nor empty");
        }
        setData(ano, mes, dia);
    }

    protected DataInstalacao() {
        // for ORM
    }

    /**
     * Constrói uma instância de Data com as mesmas caraterísticas da data
     * recebida por parâmetro.
     *
     * @param outraData a data com as características a copiar.
     */
    public DataInstalacao(DataInstalacao outraData) {
        year = outraData.year;
        month = outraData.month;
        day = outraData.day;
    }

    /**
     * Devolve o year da data.
     *
     * @return year da data
     */
    private int getAno() {
        return year;
    }

    private void setAno(int ano) {
        this.year = ano;
    }

    /**
     * Devolve o mês da data.
     *
     * @return mês da data.
     */
    private int getMes() {
        return month;
    }

    private void setMes(int mes) {
        this.month = mes;
    }

    /**
     * Devolve o day da data.
     *
     * @return day da data.
     */
    private int getDia() {
        return day;
    }

    private void setDia(int dia) {
        this.day = dia;
    }

    /**
     * Modifica o year, o mês e o day da data.
     *
     * @param ano o novo year da data.
     * @param mes o novo mês da data.
     * @param dia o novo day da data.
     */
    public final void setData(int ano, int mes, int dia) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException(
                    "mes nao e valido");
        }
        if (dia < 1 || dia > Month.obterMes(mes).numberOfDays(ano)) {
            throw new IllegalArgumentException(
                    "dia nao e valido");
        }
        if (ano < 0) {
            throw new IllegalArgumentException(
                    "ano nao e valido");
        }
        this.year = ano;
        this.month = mes;
        this.day = dia;
    }

    /**
     * Devolve a data no formato:%04d/%02d/%02d.
     *
     * @return caraterísticas da data.
     */
    public String toAnoMesDiaString() {
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    @Override
    public String toString() {
        return String.format("%04d%02d%02d", year, month, day);
    }

    /**
     * Compara a data com o objeto recebido.
     *
     * @param outroObjeto o objeto a comparar com a data.
     * @return true se o objeto recebido representar uma data equivalente à
     * data. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || getClass() != outroObjeto.getClass()) {
            return false;
        }
        DataInstalacao outraData = (DataInstalacao) outroObjeto;
        return year == outraData.year && month ==outraData.month
                && day == outraData.day;
    }

    /**
     * Compara a data com a outra data recebida por parâmetro.
     *
     * @param outraData a data a ser comparada.
     * @return o valor 0 se a outraData recebida é igual à data; o valor -1 se
     * a outraData for posterior à data; o valor 1 se a outraData for
     * anterior à data.
     */
    @Override
    public int compareTo(DataInstalacao outraData) {
        return (outraData.isLater(this)) ? -1 : (isLater(outraData)) ? 1 : 0;
    }

    /**
     * Devolve true se a data for maior do que a data recebida por parâmetro. Se
     * a data for menor ou igual à data recebida por parâmetro, devolve false.
     *
     * @param outraData a outra data com a qual se compara a data.
     * @return true se a data for maior do que a data recebida por parâmetro,
     * caso contrário devolve false.
     */
    public boolean isLater(DataInstalacao outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias > totalDias1;
    }

    public boolean isSooner(DataInstalacao outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return totalDias < totalDias1;
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     *
     * @param outraData a outra data com a qual se compara a data para calcular
     *                  a diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     * parâmetro.
     */
    public int diference(DataInstalacao outraData) {
        int totalDias = contaDias();
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve a diferença em número de dias entre a data e a data recebida por
     * parâmetro com year, mês e day.
     *
     * @param ano o year da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @param mes o mês da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @param dia o day da data com a qual se compara a data para calcular a
     *            diferença do número de dias.
     * @return diferença em número de dias entre a data e a data recebida por
     * parâmetro com year, mês e day.
     */
    public int diferenca(int ano, int mes, int dia) {
        int totalDias = contaDias();
        DataInstalacao outraData = new DataInstalacao(ano, mes, dia);
        int totalDias1 = outraData.contaDias();

        return Math.abs(totalDias - totalDias1);
    }

    /**
     * Devolve true se o year passado por parâmetro for bissexto. Se o year
     * passado por parâmetro não for bissexto, devolve false.
     *
     * @param year o year a validar.
     * @return true se o year passado por parâmetro for bissexto, caso contrário
     * devolve false.
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Devolve a data atual do sistema.
     *
     * @return a data atual do sistema.
     */
    public static DataInstalacao dataAtual() {
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        int mes = hoje.get(Calendar.MONTH) + 1;    // janeiro é representado por 0.
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        return new DataInstalacao(ano, mes, dia);
    }

    /**
     * Devolve o número de dias desde o day 1/1/1 até à data.
     *
     * @return número de dias desde o day 1/1/1 até à data.
     */
    private int contaDias() {
        int totalDias = 0;

        for (int i = 1; i < year; i++) {
            totalDias += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            totalDias += Month.obterMes(i).numberOfDays(year);
        }
        totalDias += day;

        return totalDias;
    }

    public boolean isBetween(DataInstalacao firstDate, DataInstalacao secondDate) {
        return (firstDate.isSooner(this) && secondDate.isLater(this));
    }
}
