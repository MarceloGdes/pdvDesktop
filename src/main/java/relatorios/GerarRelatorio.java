package relatorios;

import conexaoDB.ConexaoPostgres;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class GerarRelatorio {
    //irei implementar igual do porfessr
    private static final Logger LOGGER = Logger.getLogger(GerarRelatorio.class.getName());

    public void gerarRelatorioComParametro(String nomeRelatorio, int idParametro, String nomeParametro) {
        try {
            Connection conexao = ConexaoPostgres.getConnection();

            String caminhoRelatorio = "relatorio/" + nomeRelatorio + ".jasper";
            File arquivoRelatorio = new File(caminhoRelatorio);

            if (!arquivoRelatorio.exists()) {
                JOptionPane.showMessageDialog(null,
                        "Arquivo de relatório não encontrado: " + caminhoRelatorio,
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Map<String, Object> parametros = new HashMap<>();
            parametros.put(nomeParametro, idParametro);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    caminhoRelatorio, parametros, conexao);

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Relatório - " + nomeRelatorio);
            viewer.setVisible(true);

        } catch (JRException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao gerar relatório: " + nomeRelatorio, ex);
            JOptionPane.showMessageDialog(null,
                    "Erro ao gerar relatório: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro de conexão ou outro erro: ", ex);
            JOptionPane.showMessageDialog(null,
                    "Erro: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void gerarRelatorioComFiltroData(String nomeRelatorio, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        try {

            Connection conexao = ConexaoPostgres.getConnection();


            String caminhoRelatorio = "relatorio/" + nomeRelatorio + ".jasper";
            File arquivoRelatorio = new File(caminhoRelatorio);

            if (!arquivoRelatorio.exists()) {
                JOptionPane.showMessageDialog(null,
                        "Arquivo de relatório não encontrado: " + caminhoRelatorio,
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
//            Date dataInicioSql = java.sql.Date.valueOf(dataInicial); 
//            Date dataFimSql = java.sql.Date.valueOf(dataFinal);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("data_inicio_param", Timestamp.valueOf(dataInicial) );
            parametros.put("data_fim_param", Timestamp.valueOf(dataFinal));

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    caminhoRelatorio, parametros, conexao);

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Relatório - " + nomeRelatorio + " - Período: " + dataInicial + " a " + dataFinal);
            viewer.setVisible(true);

        } catch (JRException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao gerar relatório: " + nomeRelatorio, ex);
            JOptionPane.showMessageDialog(null,
                    "Erro ao gerar relatório: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro de conexão ou outro erro: ", ex);
            JOptionPane.showMessageDialog(null,
                    "Erro: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}