namespace HutongGames.PlayMaker.Actions
{
	public class SetVector2XY : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		public FsmVector2 vector2Value;

		public FsmFloat x;

		public FsmFloat y;

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

		private void DoSetVector2XYZ()
		{
		}
	}
}
