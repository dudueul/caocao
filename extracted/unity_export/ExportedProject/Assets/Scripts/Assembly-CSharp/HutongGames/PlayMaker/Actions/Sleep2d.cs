using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class Sleep2d : ComponentAction<Rigidbody2D>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSleep()
		{
		}
	}
}
