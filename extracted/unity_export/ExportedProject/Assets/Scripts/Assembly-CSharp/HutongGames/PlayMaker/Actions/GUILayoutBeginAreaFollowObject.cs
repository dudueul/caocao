namespace HutongGames.PlayMaker.Actions
{
	public class GUILayoutBeginAreaFollowObject : FsmStateAction
	{
		[RequiredField]
		public FsmGameObject gameObject;

		[RequiredField]
		public FsmFloat offsetLeft;

		[RequiredField]
		public FsmFloat offsetTop;

		[RequiredField]
		public FsmFloat width;

		[RequiredField]
		public FsmFloat height;

		public FsmBool normalized;

		public FsmString style;

		public override void Reset()
		{
		}

		public override void OnGUI()
		{
		}

		private static void DummyBeginArea()
		{
		}
	}
}
