class Planta {
    private Bodega bodega;

    public Planta(int capacidadBodega) {
        bodega = new Bodega(capacidadBodega);
    }

    public Bodega getBodega() {
        return bodega;
    }
}