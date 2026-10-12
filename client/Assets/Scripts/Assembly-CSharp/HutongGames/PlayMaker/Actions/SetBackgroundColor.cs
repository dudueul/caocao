using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class SetBackgroundColor : ComponentAction<Camera>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmColor backgroundColor;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetBackgroundColor()
		{
		}
	}
}
