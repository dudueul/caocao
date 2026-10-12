namespace HutongGames.PlayMaker.Actions
{
	public class SetVector3XYZ : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		public FsmVector3 vector3Value;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

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

		private void DoSetVector3XYZ()
		{
		}
	}
}
