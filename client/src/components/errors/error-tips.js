import React from "react";
import { Alert } from "antd";
import QueueAnim from "rc-queue-anim";
import { object } from "prop-types";

export default class  extends React.Component {
  static propTypes = {
    errors: object.isRequired
  };

  render() {
    const errors = this.props.errors ? Object.keys(this.props.errors).map((key) => {
      return (
        <Alert key={`${key}_error.code`} message={this.props.errors[key]}
               type="error" showIcon/>
      );
    }) : null;
    return (
      <QueueAnim type={"scale"}>
        {errors}
      </QueueAnim>
    );
  }
}
