namespace HutongGames.PlayMaker.Actions
{
	public class Vector2AddXY : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 vector2Variable;

		public FsmFloat addX;

		public FsmFloat addY;

		public bool everyFrame;

		public bool perSecond;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoVector2AddXYZ()
		{
		}
	}
}
