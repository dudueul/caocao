using System.Collections;

namespace nAI
{
	public interface IAction
	{
		Status GetStatus();

		IEnumerator Process();
	}
}
