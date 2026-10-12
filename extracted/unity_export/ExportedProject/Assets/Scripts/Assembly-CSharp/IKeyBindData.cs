using System.Collections.Generic;
using UnityEngine;

public interface IKeyBindData
{
	List<KeyCode> GetKeyCodes();

	KeyCode GetKeyCode(int index);

	bool Contain(KeyCode code);

	int FindIndex(KeyCode code);
}
