namespace HutongGames.PlayMaker.Actions
{
	public class SetRectFields : FsmStateAction
	{
		[RequiredField]
		public FsmRect rectVariable;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat width;

		public FsmFloat height;

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

		private void DoSetRectFields()
		{
		}
	}
}
