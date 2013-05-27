package ersteVersuche.Makler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ersteVersuche.Material.Makler;
import ersteVersuche.Services.ImmoService;

public class MaklermenuWerkzeug {
	private final MaklermenuGUI _GUI;
	ImmoService _service;

	MaklerNeuWerkzeug _maklerNeu;

	public MaklermenuWerkzeug(ImmoService service) {
		_service = service;

		_GUI = new MaklermenuGUI();
		_maklerNeu = new MaklerNeuWerkzeug();

		_GUI.GetTableModel().SetData(LadeMakler());

		_GUI.AddRefreshListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_GUI.GetTableModel().SetData(LadeMakler());
				// TODO: show data.
			}
		});

		_GUI.AddMaklerAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMakler();
			}

		});
		_GUI.AddMaklerDelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelMakler(_GUI.GetAktiveMakler());
			}

		});

		_GUI.AddMaklerUpdListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				// int column = e.getColumn();
				TableModelMakler model = (TableModelMakler) e.getSource();
				// String columnName = model.getColumnName(column);
				// Object data = model.getValueAt(row, column);
				String login = model.getLastLoginValue();
				Makler m = model.GetElement(row);
				UpdMakler(m, login);
			}
		});

	}

	private void AddMakler() {
		Makler m = _maklerNeu.ErstelleMakler();

		if (m != null && AddMaklerSQL(m)) {
			_GUI.GetTableModel().AddMakler(m);
		}
		_GUI.repaint();
	}

	private boolean AddMaklerSQL(Makler m) {
		_service.addMakler(m);
		return true;
	}

	private void DelMakler(Makler m) {

		if (m != null && DelMaklerSQL(m)) {
			_GUI.GetTableModel().DeleteMakler(m);
		}
		_GUI.repaint();
	}

	private boolean DelMaklerSQL(Makler m) {
		_service.deleteMakler(m);
		return true;
	}

	private void UpdMakler(Makler m, String login) {

		if (m != null && UpdMaklerSQL(m, login)) {
		}
		_GUI.repaint();
	}

	private boolean UpdMaklerSQL(Makler m, String login) {
		_service.AktualisiereMakler(m);
		return false;
	}

	public void ZeigeMaklerMenu() {
		_GUI.setVisible(true);
	}

	private List<Makler> LadeMakler() {
		List<Makler> lst = new ArrayList<Makler>(_service.getAllMakler());

		return lst;
	}

	public void AddWindowListener(WindowListener l) {
		_GUI.addWindowListener(l);
	}

}