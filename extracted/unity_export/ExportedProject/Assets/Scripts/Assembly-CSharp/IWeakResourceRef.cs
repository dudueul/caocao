using System.Collections.Generic;

public interface IWeakResourceRef
{
	void DisconnectResourceRef(List<string> adjustedPaths);

	void ConnectResourceRef();
}
