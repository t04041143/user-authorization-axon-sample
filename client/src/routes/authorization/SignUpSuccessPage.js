import React from "react";
import { connect } from "dva";
import DocumentTitle from "react-document-title";
import { Col, Row } from "antd";

@connect(
  (state) => ({ ...state.account })
)
export default class SignUpSuccessPage extends React.Component {
  render() {
    return (
      <DocumentTitle title='注册已成功'>
        <Row>
          <Col lg={6} md={6} sm={0}/>
          <Col lg={12} md={12} sm={24}>
            <Row>
              <Col md={6}/>
              <Col md={14}>
                {`账号${this.props.id}已注册成功`}
              </Col>
            </Row></Col>
          <Col lg={6} md={6} sm={0}/>
        </Row>
      </DocumentTitle>
    );
  }
}
