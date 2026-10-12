namespace HutongGames.PlayMaker.Actions
{
	public class Vector3ClampMagnitude : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 vector3Variable;

		[RequiredField]
		public FsmFloat maxLength;

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

		private void DoVector3ClampMagnitude()
		{
		}
	}
}
