package pt.iul.iscte.daam.fitmeet.data;

/**
 * Created by hugoc on 03/05/2017.
 */

public class MatchmakingRequest{
        private long duracaoMax;
        private long duracaoMin;
        private long horaMax;
        private long horaMin;
        private long id;
        private String local;

    public boolean isSanitized() {
        return sanitized;
    }

    public void setSanitized(boolean sanitized) {
        this.sanitized = sanitized;
    }

    public MatchmakingRequest(long duracaoMax, long duracaoMin, long horaMax, long horaMin, long id, String local, long raio, long tipo, String user, boolean sanitized) {
        this.duracaoMax = duracaoMax;
        this.duracaoMin = duracaoMin;
        this.horaMax = horaMax;
        this.horaMin = horaMin;
        this.id = id;
        this.local = local;
        this.raio = raio;
        this.tipo = tipo;
        this.user = user;
        this.sanitized = sanitized;
    }

    private long raio;
        private long tipo;
        private String user;
        private boolean sanitized;
        // todo user user


    public void setDuracaoMax(long duracaoMax) {
        this.duracaoMax = duracaoMax;
    }

    public void setDuracaoMin(long duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    @Override
    public String toString() {
        return "MatchmakingRequest{" +
                "duracaoMax=" + duracaoMax +
                ", duracaoMin=" + duracaoMin +
                ", horaMax=" + horaMax +
                ", horaMin=" + horaMin +
                ", id=" + id +
                ", local='" + local + '\'' +
                ", raio=" + raio +
                ", tipo=" + tipo +
                ", user='" + user + '\'' +
                '}';
    }

    public void setHoraMax(long horaMax) {
        this.horaMax = horaMax;
    }

    public void setHoraMin(long horaMin) {
        this.horaMin = horaMin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setRaio(long raio) {
        this.raio = raio;
    }

    public void setTipo(long tipo) {
        this.tipo = tipo;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getDuracaoMax() {
        return duracaoMax;
    }

    public long getDuracaoMin() {
        return duracaoMin;
    }

    public long getHoraMax() {
        return horaMax;
    }

    public long getHoraMin() {
        return horaMin;
    }

    public long getId() {
        return id;
    }

    public String getLocal() {
        return local;
    }

    public long getRaio() {
        return raio;
    }

    public long getTipo() {
        return tipo;
    }

    public String getUser() {
        return user;
    }

    public MatchmakingRequest(long duracaoMax, long duracaoMin, long horaMax, long horaMin, long id, String local, long raio, long tipo, String user) {

        this.duracaoMax = duracaoMax;
        this.duracaoMin = duracaoMin;
        this.horaMax = horaMax;
        this.horaMin = horaMin;
        this.id = id;
        this.local = local;
        this.raio = raio;
        this.tipo = tipo;
        this.user = user;
    }

    public MatchmakingRequest(){
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

}
