package model;

import java.time.LocalDateTime;

public class Product {

    private Integer codigo;
    private String  codigoBarras;
    private String  descricao;
    private String  descricaoComplementar;
    private String  unidade;
    private Integer codigoFornecedor;
    private Double  ipi;
    private Double  estoqueMinimo;
    private Double  estoqueMaximo;
    private Integer categoria;
    private Boolean fracionado;
    private Boolean atacado;
    private Boolean balanca;
    private Double  pesoLiquido;
    private Double  pesoBruto;
    private LocalDateTime dataCadastro;
    private Boolean numSerie;
    private String  comentario;
    private String  marca;
    private String  refFabricante;
    private String  ncm;
    private Integer diasValidade;
    private Integer codNutricional;
    private Boolean precoAberto;
    private Boolean combustivel;
    private Boolean produtoComposto;
    private String  genCodigoNcm;
    private Double  precoCusto;
    private Double  precoVenda;
    private Double  valorAtacado;

    public Product() {

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoComplementar() {
        return descricaoComplementar;
    }

    public void setDescricaoComplementar(String descricaoComplementar) {
        this.descricaoComplementar = descricaoComplementar;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(Integer codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public Double getIpi() {
        return ipi;
    }

    public void setIpi(Double ipi) {
        this.ipi = ipi;
    }

    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Double getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(Double estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Boolean getFracionado() {
        return fracionado;
    }

    public void setFracionado(Boolean fracionado) {
        this.fracionado = fracionado;
    }

    public Boolean getAtacado() {
        return atacado;
    }

    public void setAtacado(Boolean atacado) {
        this.atacado = atacado;
    }

    public Boolean getBalanca() {
        return balanca;
    }

    public void setBalanca(Boolean balanca) {
        this.balanca = balanca;
    }

    public Double getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(Double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(Boolean numSerie) {
        this.numSerie = numSerie;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRefFabricante() {
        return refFabricante;
    }

    public void setRefFabricante(String refFabricante) {
        this.refFabricante = refFabricante;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Integer getDiasValidade() {
        return diasValidade;
    }

    public void setDiasValidade(Integer diasValidade) {
        this.diasValidade = diasValidade;
    }

    public Integer getCodNutricional() {
        return codNutricional;
    }

    public void setCodNutricional(Integer codNutricional) {
        this.codNutricional = codNutricional;
    }

    public Boolean getPrecoAberto() {
        return precoAberto;
    }

    public void setPrecoAberto(Boolean precoAberto) {
        this.precoAberto = precoAberto;
    }

    public Boolean getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Boolean combustivel) {
        this.combustivel = combustivel;
    }

    public Boolean getProdutoComposto() {
        return produtoComposto;
    }

    public void setProdutoComposto(Boolean produtoComposto) {
        this.produtoComposto = produtoComposto;
    }

    public String getGenCodigoNcm() {
        return genCodigoNcm;
    }

    public void setGenCodigoNcm(String genCodigoNcm) {
        this.genCodigoNcm = genCodigoNcm;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getValorAtacado() {
        return valorAtacado;
    }

    public void setValorAtacado(Double valorAtacado) {
        this.valorAtacado = valorAtacado;
    }
}