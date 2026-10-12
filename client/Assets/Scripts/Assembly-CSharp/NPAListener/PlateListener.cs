using System;
using NPA;

namespace NPAListener
{
	public class PlateListener : INPPlateListener
	{
		public Action actionPerformedCallback;

		public Action<NXPToyResult> GetAction()
		{
			return null;
		}

		public void OnActionPerformedResult(NPResult result)
		{
		}
	}
}
