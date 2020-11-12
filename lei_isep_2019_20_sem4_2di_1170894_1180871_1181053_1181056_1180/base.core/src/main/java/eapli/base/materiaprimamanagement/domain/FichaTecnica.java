package eapli.base.materiaprimamanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.StringPredicates;


import javax.persistence.Embeddable;
import java.io.*;


public class FichaTecnica implements ValueObject {

    private String nome;
    private byte[] fichaT;

    public FichaTecnica(String nome) {
        if (StringPredicates.isNullOrEmpty(nome)) {
            throw new IllegalArgumentException(
                    "nome should neither be null nor empty");
        }
        // expression
        this.nome =  nome;
        this.fichaT = ReadByteArrayFromFileWithDataInputStream(nome);
    }

    public byte[] ReadByteArrayFromFileWithDataInputStream(String nome){
        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(nome);

            dis = new DataInputStream(fis);
            byte b[] = new byte[10];
            dis.read(b);
            return b;

        }
        catch (FileNotFoundException fe) {
            System.out.println("File not found: " + fe);
        }
        catch (IOException ioe) {
            System.out.println("Error while reading file: " + ioe);
        }
        finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            }
            catch (Exception e) {
                System.out.println("Error while closing streams" + e);
            }
        }
        return null;
    }

    public FichaTecnica(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categoria)) {
            return false;
        }

        final FichaTecnica that = (FichaTecnica) o;
        return this.nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static FichaTecnica valueOf(final String nome) {
        return new FichaTecnica(nome);
    }
}
